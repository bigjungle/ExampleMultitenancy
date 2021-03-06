/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoStepDataAtchHisSdmSuffixUpdateComponent } from 'app/entities/plan-info-step-data-atch-his-sdm-suffix/plan-info-step-data-atch-his-sdm-suffix-update.component';
import { PlanInfoStepDataAtchHisSdmSuffixService } from 'app/entities/plan-info-step-data-atch-his-sdm-suffix/plan-info-step-data-atch-his-sdm-suffix.service';
import { PlanInfoStepDataAtchHisSdmSuffix } from 'app/shared/model/plan-info-step-data-atch-his-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoStepDataAtchHisSdmSuffix Management Update Component', () => {
        let comp: PlanInfoStepDataAtchHisSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<PlanInfoStepDataAtchHisSdmSuffixUpdateComponent>;
        let service: PlanInfoStepDataAtchHisSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoStepDataAtchHisSdmSuffixUpdateComponent]
            })
                .overrideTemplate(PlanInfoStepDataAtchHisSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PlanInfoStepDataAtchHisSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanInfoStepDataAtchHisSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoStepDataAtchHisSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfoStepDataAtchHis = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoStepDataAtchHisSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfoStepDataAtchHis = entity;
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
