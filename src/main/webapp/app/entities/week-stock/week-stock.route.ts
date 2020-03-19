import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWeekStock, WeekStock } from 'app/shared/model/week-stock.model';
import { WeekStockService } from './week-stock.service';
import { WeekStockComponent } from './week-stock.component';
import { WeekStockDetailComponent } from './week-stock-detail.component';
import { WeekStockUpdateComponent } from './week-stock-update.component';

@Injectable({ providedIn: 'root' })
export class WeekStockResolve implements Resolve<IWeekStock> {
  constructor(private service: WeekStockService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWeekStock> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((weekStock: HttpResponse<WeekStock>) => {
          if (weekStock.body) {
            return of(weekStock.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WeekStock());
  }
}

export const weekStockRoute: Routes = [
  {
    path: '',
    component: WeekStockComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'jhApp.weekStock.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WeekStockDetailComponent,
    resolve: {
      weekStock: WeekStockResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhApp.weekStock.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WeekStockUpdateComponent,
    resolve: {
      weekStock: WeekStockResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhApp.weekStock.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WeekStockUpdateComponent,
    resolve: {
      weekStock: WeekStockResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhApp.weekStock.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
