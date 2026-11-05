const applicationForm = document.getElementById("applicationForm");
const message = document.getElementById("message");
const applicationsTable = document.getElementById("applicationsTable");

applicationForm.addEventListener("submit", async function (event) {
    event.preventDefault();

    const amount = document.getElementById("amount").value;
    const clientId = document.getElementById("clientId").value;

    try {
        const response = await fetch("/api/applications", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                amount: amount,
                clientId: clientId
            })
        });

        const result = await response.json();

        if (!response.ok) {
            throw new Error(result.message);
        }

        message.textContent = "Заявка успешно создана. ID: " + result.id;

        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${result.id}</td>
            <td>${result.client.firstName} ${result.client.lastName}</td>
            <td>${Number(result.amount).toFixed(2)}</td>
            <td>
                <span class="status-badge status-new">${result.status}</span>
            </td>
            <td class="score-cell">
                <span class="score-badge score-empty">Не рассчитан</span>
            </td>
            <td>
                <button type="button"
                        class="calculate-button"
                        data-id="${result.id}">
                    Рассчитать
                </button>
            </td>
            <td>${result.createdAt}</td>
        `;

        applicationsTable.appendChild(row);


    } catch (error) {
        message.textContent = "Ошибка: " + error.message;
    }
});

applicationsTable.addEventListener("click", async function (event) {

    if (!event.target.classList.contains("calculate-button")) {
        return;
    }

    const button = event.target;
    const applicationId = button.dataset.id;

    try {
        const response = await fetch(`/api/applications/${applicationId}/calculate`, {
            method: "POST"
        });

        const score = await response.json();

        if (!response.ok) {
            throw new Error("Не удалось рассчитать рейтинг");
        }

        const row = button.closest("tr");
        const scoreCell = row.querySelector(".score-cell");

        let scoreClass = "score-low";

        if (score === 50) {
            scoreClass = "score-medium";
        } else if (score === 100) {
            scoreClass = "score-high";
        }

        scoreCell.innerHTML = `<span class="score-badge ${scoreClass}">${score}</span>`;

        button.textContent = "Рассчитано";
        button.disabled = true;

    } catch (error) {
        message.textContent = "Ошибка: " + error.message;
    }
});