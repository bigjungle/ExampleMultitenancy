/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoDataAtchSdmSuffixUpdateComponent } from 'app/entities/plan-info-data-atch-sdm-suffix/plan-info-data-atch-sdm-suffix-update.component';
import { PlanInfoDataAtchSdmSuffixService } from 'app/entities/plan-info-data-atch-sdm-suffix/plan-info-data-atch-sdm-suffix.service';
import { PlanInfoDataAtchSdmSuffix } from 'app/shared/model/plan-info-data-atch-sdm-suffix.model';

describe('Component Tests', () => {
    describe('PlanInfoDataAtchSdmSuffix Management Update Component', () => {
        let comp: PlanInfoDataAtchSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<PlanInfoDataAtchSdmSuffixUpdateComponent>;
        let service: PlanInfoDataAtchSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoDataAtchSdmSuffixUpdateComponent]
            })
                .overrideTemplate(PlanInfoDataAtchSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PlanInfoDataAtchSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanInfoDataAtchSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoDataAtchSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfoDataAtch = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PlanInfoDataAtchSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.planInfoDataAtch = entity;
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
