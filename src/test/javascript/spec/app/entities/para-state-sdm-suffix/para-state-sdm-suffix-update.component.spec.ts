/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { ParaStateSdmSuffixUpdateComponent } from 'app/entities/para-state-sdm-suffix/para-state-sdm-suffix-update.component';
import { ParaStateSdmSuffixService } from 'app/entities/para-state-sdm-suffix/para-state-sdm-suffix.service';
import { ParaStateSdmSuffix } from 'app/shared/model/para-state-sdm-suffix.model';

describe('Component Tests', () => {
    describe('ParaStateSdmSuffix Management Update Component', () => {
        let comp: ParaStateSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<ParaStateSdmSuffixUpdateComponent>;
        let service: ParaStateSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [ParaStateSdmSuffixUpdateComponent]
            })
                .overrideTemplate(ParaStateSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ParaStateSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ParaStateSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ParaStateSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.paraState = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ParaStateSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.paraState = entity;
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
