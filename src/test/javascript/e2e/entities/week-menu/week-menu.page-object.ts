import { element, by, ElementFinder } from 'protractor';

export class WeekMenuComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-week-menu div table .btn-danger'));
  title = element.all(by.css('jhi-week-menu div h2#page-heading span')).first();
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

export class WeekMenuUpdatePage {
  pageTitle = element(by.id('jhi-week-menu-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  weekSelect = element(by.id('field_week'));
  sortInput = element(by.id('field_sort'));
  lastModifiedDateInput = element(by.id('field_lastModifiedDate'));
  lastModifiedByInput = element(by.id('field_lastModifiedBy'));

  foodSelect = element(by.id('field_food'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setWeekSelect(week: string): Promise<void> {
    await this.weekSelect.sendKeys(week);
  }

  async getWeekSelect(): Promise<string> {
    return await this.weekSelect.element(by.css('option:checked')).getText();
  }

  async weekSelectLastOption(): Promise<void> {
    await this.weekSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setSortInput(sort: string): Promise<void> {
    await this.sortInput.sendKeys(sort);
  }

  async getSortInput(): Promise<string> {
    return await this.sortInput.getAttribute('value');
  }

  async setLastModifiedDateInput(lastModifiedDate: string): Promise<void> {
    await this.lastModifiedDateInput.sendKeys(lastModifiedDate);
  }

  async getLastModifiedDateInput(): Promise<string> {
    return await this.lastModifiedDateInput.getAttribute('value');
  }

  async setLastModifiedByInput(lastModifiedBy: string): Promise<void> {
    await this.lastModifiedByInput.sendKeys(lastModifiedBy);
  }

  async getLastModifiedByInput(): Promise<string> {
    return await this.lastModifiedByInput.getAttribute('value');
  }

  async foodSelectLastOption(): Promise<void> {
    await this.foodSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async foodSelectOption(option: string): Promise<void> {
    await this.foodSelect.sendKeys(option);
  }

  getFoodSelect(): ElementFinder {
    return this.foodSelect;
  }

  async getFoodSelectedOption(): Promise<string> {
    return await this.foodSelect.element(by.css('option:checked')).getText();
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

export class WeekMenuDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-weekMenu-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-weekMenu'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
