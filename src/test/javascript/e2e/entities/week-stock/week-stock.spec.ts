import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { WeekStockComponentsPage, WeekStockDeleteDialog, WeekStockUpdatePage } from './week-stock.page-object';

const expect = chai.expect;

describe('WeekStock e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let weekStockComponentsPage: WeekStockComponentsPage;
  let weekStockUpdatePage: WeekStockUpdatePage;
  let weekStockDeleteDialog: WeekStockDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load WeekStocks', async () => {
    await navBarPage.goToEntity('week-stock');
    weekStockComponentsPage = new WeekStockComponentsPage();
    await browser.wait(ec.visibilityOf(weekStockComponentsPage.title), 5000);
    expect(await weekStockComponentsPage.getTitle()).to.eq('jhApp.weekStock.home.title');
    await browser.wait(ec.or(ec.visibilityOf(weekStockComponentsPage.entities), ec.visibilityOf(weekStockComponentsPage.noResult)), 1000);
  });

  it('should load create WeekStock page', async () => {
    await weekStockComponentsPage.clickOnCreateButton();
    weekStockUpdatePage = new WeekStockUpdatePage();
    expect(await weekStockUpdatePage.getPageTitle()).to.eq('jhApp.weekStock.home.createOrEditLabel');
    await weekStockUpdatePage.cancel();
  });

  it('should create and save WeekStocks', async () => {
    const nbButtonsBeforeCreate = await weekStockComponentsPage.countDeleteButtons();

    await weekStockComponentsPage.clickOnCreateButton();

    await promise.all([
      weekStockUpdatePage.setShopSNInput('shopSN'),
      weekStockUpdatePage.setShopNameInput('shopName'),
      weekStockUpdatePage.setFoodQuantityInput('5'),
      weekStockUpdatePage.weekMenuSelectLastOption()
    ]);

    expect(await weekStockUpdatePage.getShopSNInput()).to.eq('shopSN', 'Expected ShopSN value to be equals to shopSN');
    expect(await weekStockUpdatePage.getShopNameInput()).to.eq('shopName', 'Expected ShopName value to be equals to shopName');
    expect(await weekStockUpdatePage.getFoodQuantityInput()).to.eq('5', 'Expected foodQuantity value to be equals to 5');

    await weekStockUpdatePage.save();
    expect(await weekStockUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await weekStockComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last WeekStock', async () => {
    const nbButtonsBeforeDelete = await weekStockComponentsPage.countDeleteButtons();
    await weekStockComponentsPage.clickOnLastDeleteButton();

    weekStockDeleteDialog = new WeekStockDeleteDialog();
    expect(await weekStockDeleteDialog.getDialogTitle()).to.eq('jhApp.weekStock.delete.question');
    await weekStockDeleteDialog.clickOnConfirmButton();

    expect(await weekStockComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
