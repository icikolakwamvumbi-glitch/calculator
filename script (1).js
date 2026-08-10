const display = document.getElementById("display");
const buttons = document.querySelectorAll("button");

let current = "";
let previous = "";
let operator = "";

buttons.forEach(button => {
    button.addEventListener("click", () => {
        const value = button.textContent.trim();

        // This section of code are for numbers Numbers
        if (!isNaN(value)) {
            current += value;
            updateDisplay(current);
            return;
        }

        // This section of code are for Decimals
        if (value === ".") {
            if (!current.includes(".")) {
                current += ".";
            }
            updateDisplay(current || "0");
            return;
        }

        switch (value) {

            case "+":
            case "-":
            case "x":
            case "÷":
                if (current === "") return;

                previous = current;
                current = "";
                operator = value;
                break;

            case "=":
                calculate();
                break;

            case "C":
                current = "";
                previous = "";
                operator = "";
                updateDisplay("0");
                break;

            case "CE":
                current = "";
                updateDisplay("0");
                break;

            case "%":
                current = (parseFloat(current) / 100).toString();
                updateDisplay(current);
                break;

            case "±":
                current = (-parseFloat(current)).toString();
                updateDisplay(current);
                break;

            case "x²":
                current = (Math.pow(parseFloat(current), 2)).toString();
                updateDisplay(current);
                break;

            case "²√x":
                current = Math.sqrt(parseFloat(current)).toString();
                updateDisplay(current);
                break;

            case "¹/x":
                current = (1 / parseFloat(current)).toString();
                updateDisplay(current);
                break;
        }
    });
});

function calculate() {

    let num1 = parseFloat(previous);
    let num2 = parseFloat(current);
    let result = 0;

    switch (operator) {
        case "+":
            result = num1 + num2;
            break;

        case "-":
            result = num1 - num2;
            break;

        case "x":
            result = num1 * num2;
            break;

        case "÷":
            result = num1 / num2;
            break;
    }

    current = result.toString();
    previous = "";
    operator = "";

    updateDisplay(current);
}

function updateDisplay(value) {
    display.textContent = value;
}
