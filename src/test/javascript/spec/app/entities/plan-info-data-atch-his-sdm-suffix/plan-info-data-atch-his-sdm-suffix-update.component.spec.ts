/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoDataAtchHisSdmSuffixUpdateComponent } from 'app/entities/plan-info-data-atch-his-sdm-suffix/plan-info-data-atch-his-sdm-suffix-update.component';
import { PlanInfoDataAtchHisSdmSuffixService } from 'app/entities/plan-info-data-atch-his-sdm-suffix/plan-info-data-atch-his-sdm-suffix.service';
import { PlanInfoDataAtchHisSdmSuffix } from 'app/shared/model/plan-info-data-atch-his-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoDataAtchHisSdmSuffix Management Update Component', () => {
        let comp: PlanInfoDataAtchHisSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<PlanInfoDataAtchHisSdmSuffixUpdateComponent>;
        let service: PlanInfoDataAtchHisSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoDataAtchHisSdmSuffixUpdateComponent]
            })
                .overrideTemplate(PlanInfoDataAtchHisSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PlanInfoDataAtchHisSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanInfoDataAtchHisSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoDataAtchHisSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfoDataAtchHis = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoDataAtchHisSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfoDataAtchHis = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
