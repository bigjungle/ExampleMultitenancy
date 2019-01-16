/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoStepDataAtchSdmSuffixUpdateComponent } from 'app/entities/plan-info-step-data-atch-sdm-suffix/plan-info-step-data-atch-sdm-suffix-update.component';
import { PlanInfoStepDataAtchSdmSuffixService } from 'app/entities/plan-info-step-data-atch-sdm-suffix/plan-info-step-data-atch-sdm-suffix.service';
import { PlanInfoStepDataAtchSdmSuffix } from 'app/shared/model/plan-info-step-data-atch-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoStepDataAtchSdmSuffix Management Update Component', () => {
        let comp: PlanInfoStepDataAtchSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<PlanInfoStepDataAtchSdmSuffixUpdateComponent>;
        let service: PlanInfoStepDataAtchSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoStepDataAtchSdmSuffixUpdateComponent]
            })
                .overrideTemplate(PlanInfoStepDataAtchSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PlanInfoStepDataAtchSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanInfoStepDataAtchSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoStepDataAtchSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfoStepDataAtch = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoStepDataAtchSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfoStepDataAtch = entity;
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
