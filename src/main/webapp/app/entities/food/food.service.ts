import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFood } from 'app/shared/model/food.model';

type EntityResponseType = HttpResponse<IFood>;
type EntityArrayResponseType = HttpResponse<IFood[]>;

@Injectable({ providedIn: 'root' })
export class FoodService {
  public resourceUrl = SERVER_API_URL + 'api/foods';

  constructor(protected http: HttpClient) {}

  create(food: IFood): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(food);
    return this.http
      .post<IFood>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(food: IFood): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(food);
    return this.http
      .put<IFood>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFood>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFood[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(food: IFood): IFood {
    const copy: IFood = Object.assign({}, food, {
      createdDate: food.createdDate && food.createdDate.isValid() ? food.createdDate.toJSON() : undefined,
      lastModifiedDate: food.lastModifiedDate && food.lastModifiedDate.isValid() ? food.lastModifiedDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.lastModifiedDate = res.body.lastModifiedDate ? moment(res.body.lastModifiedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((food: IFood) => {
        food.createdDate = food.createdDate ? moment(food.createdDate) : undefined;
        food.lastModifiedDate = food.lastModifiedDate ? moment(food.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
