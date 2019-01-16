/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { RmsRoleSdmSuffixUpdateComponent } from 'app/entities/rms-role-sdm-suffix/rms-role-sdm-suffix-update.component';
import { RmsRoleSdmSuffixService } from 'app/entities/rms-role-sdm-suffix/rms-role-sdm-suffix.service';
import { RmsRoleSdmSuffix } from 'app/shared/model/rms-role-sdm-suffix.model';

describe('Component Tests', () => {
    describe('RmsRoleSdmSuffix Management Update Component', () => {
        let comp: RmsRoleSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<RmsRoleSdmSuffixUpdateComponent>;
        let service: RmsRoleSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [RmsRoleSdmSuffixUpdateComponent]
            })
                .overrideTemplate(RmsRoleSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RmsRoleSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RmsRoleSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new RmsRoleSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.rmsRole = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new RmsRoleSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.rmsRole = entity;
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
