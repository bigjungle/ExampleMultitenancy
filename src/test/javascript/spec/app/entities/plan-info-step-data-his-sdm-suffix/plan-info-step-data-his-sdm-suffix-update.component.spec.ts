/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoStepDataHisSdmSuffixUpdateComponent } from 'app/entities/plan-info-step-data-his-sdm-suffix/plan-info-step-data-his-sdm-suffix-update.component';
import { PlanInfoStepDataHisSdmSuffixService } from 'app/entities/plan-info-step-data-his-sdm-suffix/plan-info-step-data-his-sdm-suffix.service';
import { PlanInfoStepDataHisSdmSuffix } from 'app/shared/model/plan-info-step-data-his-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoStepDataHisSdmSuffix Management Update Component', () => {
        let comp: PlanInfoStepDataHisSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<PlanInfoStepDataHisSdmSuffixUpdateComponent>;
        let service: PlanInfoStepDataHisSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoStepDataHisSdmSuffixUpdateComponent]
            })
                .overrideTemplate(PlanInfoStepDataHisSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PlanInfoStepDataHisSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanInfoStepDataHisSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoStepDataHisSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfoStepDataHis = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoStepDataHisSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfoStepDataHis = entity;
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
