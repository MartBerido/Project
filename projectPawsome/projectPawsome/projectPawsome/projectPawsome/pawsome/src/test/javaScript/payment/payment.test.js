const { validateForm } = require('./payment.js');
const { JSDOM } = require('jsdom');
const { document } = new JSDOM('').window;

global.document = document;
global.alert = jest.fn();

test('Return true if the form is valid', () => {

    document.body.innerHTML = `
    <form onsubmit="return validateForm()">
      <input type="text" id="creditCardNumber" value="1234123412341234">
      <input type="text" id="expirationMonth" value="05">
      <input type="text" id="expirationYear" value="2025">
      <input type="text" id="cvc" value="123">
      <input type="submit" value="Submit">
    </form>
  `;

    expect(validateForm()).toBe(true);
});

test('Return true if the form is invalid', () => {

    document.body.innerHTML = `
    <form onsubmit="return validateForm()">
      <input type="text" id="creditCardNumber" value="1234"> 
      <input type="text" id="expirationMonth" value="13"> 
      <input type="text" id="expirationYear" value="20">
      <input type="text" id="cvc" value="12"> 
      <input type="submit" value="Submit">
    </form>
  `;

    expect(validateForm()).toBe(false);
});
