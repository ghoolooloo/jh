import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWeekMenu } from 'app/shared/model/week-menu.model';

@Component({
  selector: 'jhi-week-menu-detail',
  templateUrl: './week-menu-detail.component.html'
})
export class WeekMenuDetailComponent implements OnInit {
  weekMenu: IWeekMenu | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ weekMenu }) => (this.weekMenu = weekMenu));
  }

  previousState(): void {
    window.history.back();
  }
}
