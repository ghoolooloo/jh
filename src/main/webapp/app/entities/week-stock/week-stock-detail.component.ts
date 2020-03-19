import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWeekStock } from 'app/shared/model/week-stock.model';

@Component({
  selector: 'jhi-week-stock-detail',
  templateUrl: './week-stock-detail.component.html'
})
export class WeekStockDetailComponent implements OnInit {
  weekStock: IWeekStock | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ weekStock }) => (this.weekStock = weekStock));
  }

  previousState(): void {
    window.history.back();
  }
}
