import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWeekMenu, WeekMenu } from 'app/shared/model/week-menu.model';
import { WeekMenuService } from './week-menu.service';
import { WeekMenuComponent } from './week-menu.component';
import { WeekMenuDetailComponent } from './week-menu-detail.component';
import { WeekMenuUpdateComponent } from './week-menu-update.component';

@Injectable({ providedIn: 'root' })
export class WeekMenuResolve implements Resolve<IWeekMenu> {
  constructor(private service: WeekMenuService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWeekMenu> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((weekMenu: HttpResponse<WeekMenu>) => {
          if (weekMenu.body) {
            return of(weekMenu.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WeekMenu());
  }
}

export const weekMenuRoute: Routes = [
  {
    path: '',
    component: WeekMenuComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhApp.weekMenu.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WeekMenuDetailComponent,
    resolve: {
      weekMenu: WeekMenuResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhApp.weekMenu.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WeekMenuUpdateComponent,
    resolve: {
      weekMenu: WeekMenuResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhApp.weekMenu.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WeekMenuUpdateComponent,
    resolve: {
      weekMenu: WeekMenuResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhApp.weekMenu.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
