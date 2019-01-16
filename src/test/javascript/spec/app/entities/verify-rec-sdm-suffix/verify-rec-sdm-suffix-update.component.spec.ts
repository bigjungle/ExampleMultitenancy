/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { VerifyRecSdmSuffixUpdateComponent } from 'app/entities/verify-rec-sdm-suffix/verify-rec-sdm-suffix-update.component';
import { VerifyRecSdmSuffixService } from 'app/entities/verify-rec-sdm-suffix/verify-rec-sdm-suffix.service';
import { VerifyRecSdmSuffix } from 'app/shared/model/verify-rec-sdm-suffix.model';

describe('Component Tests', () => {
    describe('VerifyRecSdmSuffix Management Update Component', () => {
        let comp: VerifyRecSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<VerifyRecSdmSuffixUpdateComponent>;
        let service: VerifyRecSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [VerifyRecSdmSuffixUpdateComponent]
            })
                .overrideTemplate(VerifyRecSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VerifyRecSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VerifyRecSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new VerifyRecSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.verifyRec = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new VerifyRecSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.verifyRec = entity;
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
