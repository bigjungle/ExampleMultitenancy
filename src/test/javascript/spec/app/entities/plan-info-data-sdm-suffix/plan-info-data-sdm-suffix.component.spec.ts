/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoDataSdmSuffixComponent } from 'app/entities/plan-info-data-sdm-suffix/plan-info-data-sdm-suffix.component';
import { PlanInfoDataSdmSuffixService } from 'app/entities/plan-info-data-sdm-suffix/plan-info-data-sdm-suffix.service';
import { PlanInfoDataSdmSuffix } from 'app/shared/model/plan-info-data-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoDataSdmSuffix Management Component', () => {
        let comp: PlanInfoDataSdmSuffixComponent;
        let fixture: ComponentFixture<PlanInfoDataSdmSuffixComponent>;
        let service: PlanInfoDataSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoDataSdmSuffixComponent],
                providers: [
                    {
                        provide: ActivatedRoute,
                        useValue: {
                            data: {
                                subscribe: (fn: (value: Data) => void) =>
                                    fn({
                                        pagingParams: {
                                            predicate: 'id',
                                            reverse: false,
                                            page: 0
                                        }
                                    })
                            }
                        }
                    }
                ]
            })
                .overrideTemplate(PlanInfoDataSdmSuffixComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PlanInfoDataSdmSuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanInfoDataSdmSuffixService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new PlanInfoDataSdmSuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.planInfoData[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });

        it('should load a page', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new PlanInfoDataSdmSuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.loadPage(1);

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.planInfoData[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });

        it('should re-initialize the page', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new PlanInfoDataSdmSuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.loadPage(1);
            comp.reset();

            // THEN
            expect(comp.page).toEqual(0);
            expect(service.query).toHaveBeenCalledTimes(2);
            expect(comp.planInfoData[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
        it('should calculate the sort attribute for an id', () => {
            // WHEN
            const result = comp.sort();

            // THEN
            expect(result).toEqual(['id,asc']);
        });

        it('should calculate the sort attribute for a non-id attribute', () => {
            // GIVEN
            comp.predicate = 'name';

            // WHEN
            const result = comp.sort();

            // THEN
            expect(result).toEqual(['name,asc', 'id']);
        });
    });
});
