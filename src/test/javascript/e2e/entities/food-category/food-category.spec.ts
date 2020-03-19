import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { FoodCategoryComponentsPage, FoodCategoryDeleteDialog, FoodCategoryUpdatePage } from './food-category.page-object';

const expect = chai.expect;

describe('FoodCategory e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let foodCategoryComponentsPage: FoodCategoryComponentsPage;
  let foodCategoryUpdatePage: FoodCategoryUpdatePage;
  let foodCategoryDeleteDialog: FoodCategoryDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load FoodCategories', async () => {
    await navBarPage.goToEntity('food-category');
    foodCategoryComponentsPage = new FoodCategoryComponentsPage();
    await browser.wait(ec.visibilityOf(foodCategoryComponentsPage.title), 5000);
    expect(await foodCategoryComponentsPage.getTitle()).to.eq('jhApp.foodCategory.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(foodCategoryComponentsPage.entities), ec.visibilityOf(foodCategoryComponentsPage.noResult)),
      1000
    );
  });

  it('should load create FoodCategory page', async () => {
    await foodCategoryComponentsPage.clickOnCreateButton();
    foodCategoryUpdatePage = new FoodCategoryUpdatePage();
    expect(await foodCategoryUpdatePage.getPageTitle()).to.eq('jhApp.foodCategory.home.createOrEditLabel');
    await foodCategoryUpdatePage.cancel();
  });

  it('should create and save FoodCategories', async () => {
    const nbButtonsBeforeCreate = await foodCategoryComponentsPage.countDeleteButtons();

    await foodCategoryComponentsPage.clickOnCreateButton();

    await promise.all([
      foodCategoryUpdatePage.setNameInput('name'),
      foodCategoryUpdatePage.setSnInput('sn'),
      foodCategoryUpdatePage.setIconInput('icon'),
      foodCategoryUpdatePage.setSortInput('5'),
      foodCategoryUpdatePage.setCreatedByInput('createdBy'),
      foodCategoryUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      foodCategoryUpdatePage.setLastModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      foodCategoryUpdatePage.setLastModifiedByInput('lastModifiedBy')
    ]);

    expect(await foodCategoryUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await foodCategoryUpdatePage.getSnInput()).to.eq('sn', 'Expected Sn value to be equals to sn');
    expect(await foodCategoryUpdatePage.getIconInput()).to.eq('icon', 'Expected Icon value to be equals to icon');
    expect(await foodCategoryUpdatePage.getSortInput()).to.eq('5', 'Expected sort value to be equals to 5');
    expect(await foodCategoryUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await foodCategoryUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await foodCategoryUpdatePage.getLastModifiedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastModifiedDate value to be equals to 2000-12-31'
    );
    expect(await foodCategoryUpdatePage.getLastModifiedByInput()).to.eq(
      'lastModifiedBy',
      'Expected LastModifiedBy value to be equals to lastModifiedBy'
    );

    await foodCategoryUpdatePage.save();
    expect(await foodCategoryUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await foodCategoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last FoodCategory', async () => {
    const nbButtonsBeforeDelete = await foodCategoryComponentsPage.countDeleteButtons();
    await foodCategoryComponentsPage.clickOnLastDeleteButton();

    foodCategoryDeleteDialog = new FoodCategoryDeleteDialog();
    expect(await foodCategoryDeleteDialog.getDialogTitle()).to.eq('jhApp.foodCategory.delete.question');
    await foodCategoryDeleteDialog.clickOnConfirmButton();

    expect(await foodCategoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
