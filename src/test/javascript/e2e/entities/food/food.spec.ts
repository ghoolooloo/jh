import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { FoodComponentsPage, FoodDeleteDialog, FoodUpdatePage } from './food.page-object';

const expect = chai.expect;

describe('Food e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let foodComponentsPage: FoodComponentsPage;
  let foodUpdatePage: FoodUpdatePage;
  let foodDeleteDialog: FoodDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Foods', async () => {
    await navBarPage.goToEntity('food');
    foodComponentsPage = new FoodComponentsPage();
    await browser.wait(ec.visibilityOf(foodComponentsPage.title), 5000);
    expect(await foodComponentsPage.getTitle()).to.eq('jhApp.food.home.title');
    await browser.wait(ec.or(ec.visibilityOf(foodComponentsPage.entities), ec.visibilityOf(foodComponentsPage.noResult)), 1000);
  });

  it('should load create Food page', async () => {
    await foodComponentsPage.clickOnCreateButton();
    foodUpdatePage = new FoodUpdatePage();
    expect(await foodUpdatePage.getPageTitle()).to.eq('jhApp.food.home.createOrEditLabel');
    await foodUpdatePage.cancel();
  });

  it('should create and save Foods', async () => {
    const nbButtonsBeforeCreate = await foodComponentsPage.countDeleteButtons();

    await foodComponentsPage.clickOnCreateButton();

    await promise.all([
      foodUpdatePage.setNameInput('name'),
      foodUpdatePage.setSnInput('sn'),
      foodUpdatePage.setThumbnailInput('thumbnail'),
      foodUpdatePage.setPictureInput('picture'),
      foodUpdatePage.setPriceInput('5'),
      foodUpdatePage.setOriginalPriceInput('5'),
      foodUpdatePage.setCostInput('5'),
      foodUpdatePage.setPackingFeeInput('5'),
      foodUpdatePage.setDescInput('desc'),
      foodUpdatePage.setSortInput('5'),
      foodUpdatePage.feedingModeSelectLastOption(),
      foodUpdatePage.setCreatedByInput('createdBy'),
      foodUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      foodUpdatePage.setLastModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      foodUpdatePage.setLastModifiedByInput('lastModifiedBy'),
      foodUpdatePage.categorySelectLastOption()
    ]);

    expect(await foodUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await foodUpdatePage.getSnInput()).to.eq('sn', 'Expected Sn value to be equals to sn');
    expect(await foodUpdatePage.getThumbnailInput()).to.eq('thumbnail', 'Expected Thumbnail value to be equals to thumbnail');
    expect(await foodUpdatePage.getPictureInput()).to.eq('picture', 'Expected Picture value to be equals to picture');
    expect(await foodUpdatePage.getPriceInput()).to.eq('5', 'Expected price value to be equals to 5');
    expect(await foodUpdatePage.getOriginalPriceInput()).to.eq('5', 'Expected originalPrice value to be equals to 5');
    expect(await foodUpdatePage.getCostInput()).to.eq('5', 'Expected cost value to be equals to 5');
    expect(await foodUpdatePage.getPackingFeeInput()).to.eq('5', 'Expected packingFee value to be equals to 5');
    expect(await foodUpdatePage.getDescInput()).to.eq('desc', 'Expected Desc value to be equals to desc');
    expect(await foodUpdatePage.getSortInput()).to.eq('5', 'Expected sort value to be equals to 5');
    expect(await foodUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await foodUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await foodUpdatePage.getLastModifiedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastModifiedDate value to be equals to 2000-12-31'
    );
    expect(await foodUpdatePage.getLastModifiedByInput()).to.eq(
      'lastModifiedBy',
      'Expected LastModifiedBy value to be equals to lastModifiedBy'
    );

    await foodUpdatePage.save();
    expect(await foodUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await foodComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Food', async () => {
    const nbButtonsBeforeDelete = await foodComponentsPage.countDeleteButtons();
    await foodComponentsPage.clickOnLastDeleteButton();

    foodDeleteDialog = new FoodDeleteDialog();
    expect(await foodDeleteDialog.getDialogTitle()).to.eq('jhApp.food.delete.question');
    await foodDeleteDialog.clickOnConfirmButton();

    expect(await foodComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
