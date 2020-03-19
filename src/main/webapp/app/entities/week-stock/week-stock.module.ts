import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhSharedModule } from 'app/shared/shared.module';
import { WeekStockComponent } from './week-stock.component';
import { WeekStockDetailComponent } from './week-stock-detail.component';
import { WeekStockUpdateComponent } from './week-stock-update.component';
import { WeekStockDeleteDialogComponent } from './week-stock-delete-dialog.component';
import { weekStockRoute } from './week-stock.route';

@NgModule({
  imports: [JhSharedModule, RouterModule.forChild(weekStockRoute)],
  declarations: [WeekStockComponent, WeekStockDetailComponent, WeekStockUpdateComponent, WeekStockDeleteDialogComponent],
  entryComponents: [WeekStockDeleteDialogComponent]
})
export class JhWeekStockModule {}
