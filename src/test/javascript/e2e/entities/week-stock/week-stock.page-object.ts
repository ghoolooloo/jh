import { element, by, ElementFinder } from 'protractor';

export class WeekStockComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-week-stock div table .btn-danger'));
  title = element.all(by.css('jhi-week-stock div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class WeekStockUpdatePage {
  pageTitle = element(by.id('jhi-week-stock-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  shopSNInput = element(by.id('field_shopSN'));
  shopNameInput = element(by.id('field_shopName'));
  foodQuantityInput = element(by.id('field_foodQuantity'));

  weekMenuSelect = element(by.id('field_weekMenu'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setShopSNInput(shopSN: string): Promise<void> {
    await this.shopSNInput.sendKeys(shopSN);
  }

  async getShopSNInput(): Promise<string> {
    return await this.shopSNInput.getAttribute('value');
  }

  async setShopNameInput(shopName: string): Promise<void> {
    await this.shopNameInput.sendKeys(shopName);
  }

  async getShopNameInput(): Promise<string> {
    return await this.shopNameInput.getAttribute('value');
  }

  async setFoodQuantityInput(foodQuantity: string): Promise<void> {
    await this.foodQuantityInput.sendKeys(foodQuantity);
  }

  async getFoodQuantityInput(): Promise<string> {
    return await this.foodQuantityInput.getAttribute('value');
  }

  async weekMenuSelectLastOption(): Promise<void> {
    await this.weekMenuSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async weekMenuSelectOption(option: string): Promise<void> {
    await this.weekMenuSelect.sendKeys(option);
  }

  getWeekMenuSelect(): ElementFinder {
    return this.weekMenuSelect;
  }

  async getWeekMenuSelectedOption(): Promise<string> {
    return await this.weekMenuSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class WeekStockDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-weekStock-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-weekStock'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
