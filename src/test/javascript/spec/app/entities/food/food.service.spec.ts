import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { FoodService } from 'app/entities/food/food.service';
import { IFood, Food } from 'app/shared/model/food.model';
import { FeedingMode } from 'app/shared/model/enumerations/feeding-mode.model';

describe('Service Tests', () => {
  describe('Food Service', () => {
    let injector: TestBed;
    let service: FoodService;
    let httpMock: HttpTestingController;
    let elemDefault: IFood;
    let expectedResult: IFood | IFood[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(FoodService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Food(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        'AAAAAAA',
        0,
        FeedingMode.DINNER,
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Food', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            lastModifiedDate: currentDate
          },
          returnedFromService
        );

        service.create(new Food()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Food', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            sn: 'BBBBBB',
            thumbnail: 'BBBBBB',
            picture: 'BBBBBB',
            price: 1,
            originalPrice: 1,
            cost: 1,
            packingFee: 1,
            desc: 'BBBBBB',
            sort: 1,
            feedingMode: 'BBBBBB',
            createdBy: 'BBBBBB',
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedBy: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            lastModifiedDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Food', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            sn: 'BBBBBB',
            thumbnail: 'BBBBBB',
            picture: 'BBBBBB',
            price: 1,
            originalPrice: 1,
            cost: 1,
            packingFee: 1,
            desc: 'BBBBBB',
            sort: 1,
            feedingMode: 'BBBBBB',
            createdBy: 'BBBBBB',
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedBy: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            lastModifiedDate: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Food', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
