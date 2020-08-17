/// <reference types="Cypress" />

describe("Login suite", () => {
  it("Login success", () => {
    const email = "visit0344314@gmail.com";
    const password = "testing1234";
    cy.visit("https://demo.realworld.io/#/");
    cy.contains("a.nav-link", "Sign in").click();
    cy.get("[placeholder=Email]").type(email);
    cy.get("[placeholder=Password]").type(password);
    cy.get("form").submit();
    cy.contains("a.nav-link", "Your Feed").should("be.visible");
  });
  it("Login Failure", () => {
    const email = "visit0344314@gmail.com";
    const password = "testing12345";
    cy.visit("https://demo.realworld.io/#/");
    cy.contains("a.nav-link", "Sign in").click();
    cy.get("[placeholder=Email]").type(email);
    cy.get("[placeholder=Password]").type(password);
    cy.get("form").submit();
    cy.contains("li.ng-binding", "email or password is invalid");
  });
});
