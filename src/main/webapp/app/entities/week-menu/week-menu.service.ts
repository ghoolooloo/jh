import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWeekMenu } from 'app/shared/model/week-menu.model';

type EntityResponseType = HttpResponse<IWeekMenu>;
type EntityArrayResponseType = HttpResponse<IWeekMenu[]>;

@Injectable({ providedIn: 'root' })
export class WeekMenuService {
  public resourceUrl = SERVER_API_URL + 'api/week-menus';

  constructor(protected http: HttpClient) {}

  create(weekMenu: IWeekMenu): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(weekMenu);
    return this.http
      .post<IWeekMenu>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(weekMenu: IWeekMenu): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(weekMenu);
    return this.http
      .put<IWeekMenu>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IWeekMenu>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IWeekMenu[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(weekMenu: IWeekMenu): IWeekMenu {
    const copy: IWeekMenu = Object.assign({}, weekMenu, {
      lastModifiedDate: weekMenu.lastModifiedDate && weekMenu.lastModifiedDate.isValid() ? weekMenu.lastModifiedDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.lastModifiedDate = res.body.lastModifiedDate ? moment(res.body.lastModifiedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((weekMenu: IWeekMenu) => {
        weekMenu.lastModifiedDate = weekMenu.lastModifiedDate ? moment(weekMenu.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
