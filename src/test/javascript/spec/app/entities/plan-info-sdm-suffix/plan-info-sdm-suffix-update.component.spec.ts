/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoSdmSuffixUpdateComponent } from 'app/entities/plan-info-sdm-suffix/plan-info-sdm-suffix-update.component';
import { PlanInfoSdmSuffixService } from 'app/entities/plan-info-sdm-suffix/plan-info-sdm-suffix.service';
import { PlanInfoSdmSuffix } from 'app/shared/model/plan-info-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoSdmSuffix Management Update Component', () => {
        let comp: PlanInfoSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<PlanInfoSdmSuffixUpdateComponent>;
        let service: PlanInfoSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoSdmSuffixUpdateComponent]
            })
                .overrideTemplate(PlanInfoSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PlanInfoSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanInfoSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfo = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfo = entity;
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
