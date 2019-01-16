/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { RmsNodeSdmSuffixUpdateComponent } from 'app/entities/rms-node-sdm-suffix/rms-node-sdm-suffix-update.component';
import { RmsNodeSdmSuffixService } from 'app/entities/rms-node-sdm-suffix/rms-node-sdm-suffix.service';
import { RmsNodeSdmSuffix } from 'app/shared/model/rms-node-sdm-suffix.model';

describe('Component Tests', () => {
    describe('RmsNodeSdmSuffix Management Update Component', () => {
        let comp: RmsNodeSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<RmsNodeSdmSuffixUpdateComponent>;
        let service: RmsNodeSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [RmsNodeSdmSuffixUpdateComponent]
            })
                .overrideTemplate(RmsNodeSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RmsNodeSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RmsNodeSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new RmsNodeSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.rmsNode = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new RmsNodeSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.rmsNode = entity;
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
