function validateForm() {

    let creditCardNumber = document.getElementById('creditCardNumber').value;
    let expirationMonth = document.getElementById('expirationMonth').value;
    let expirationYear = document.getElementById('expirationYear').value;
    let cvc = document.getElementById('cvc').value;

    if (!/^\d{16}$/.test(creditCardNumber)) {
        alert('Invalid credit card number. It must be a 16-digit number.');
        return false;
    }

    if (!/^(0?[1-9]|1[0-2])$/.test(expirationMonth)) {
        alert('Invalid expiration month. It must be a 2-digit number between 1 and 12.');
        return false;
    }

    if (!/^\d{4}$/.test(expirationYear)) {
        alert('Invalid expiration year. It must be a 4-digit number.');
        return false;
    }

    if (!/^\d{3,4}$/.test(cvc)) {
        alert('Invalid CVC. It must be a 3 or 4-digit number.');
        return false;
    }

    return true;
}

module.exports = { validateForm };