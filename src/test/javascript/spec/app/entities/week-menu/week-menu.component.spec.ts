import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhTestModule } from '../../../test.module';
import { WeekMenuComponent } from 'app/entities/week-menu/week-menu.component';
import { WeekMenuService } from 'app/entities/week-menu/week-menu.service';
import { WeekMenu } from 'app/shared/model/week-menu.model';

describe('Component Tests', () => {
  describe('WeekMenu Management Component', () => {
    let comp: WeekMenuComponent;
    let fixture: ComponentFixture<WeekMenuComponent>;
    let service: WeekMenuService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhTestModule],
        declarations: [WeekMenuComponent]
      })
        .overrideTemplate(WeekMenuComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WeekMenuComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WeekMenuService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WeekMenu(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.weekMenus && comp.weekMenus[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
