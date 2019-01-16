/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { ParaClassSdmSuffixUpdateComponent } from 'app/entities/para-class-sdm-suffix/para-class-sdm-suffix-update.component';
import { ParaClassSdmSuffixService } from 'app/entities/para-class-sdm-suffix/para-class-sdm-suffix.service';
import { ParaClassSdmSuffix } from 'app/shared/model/para-class-sdm-suffix.model';

describe('Component Tests', () => {
    describe('ParaClassSdmSuffix Management Update Component', () => {
        let comp: ParaClassSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<ParaClassSdmSuffixUpdateComponent>;
        let service: ParaClassSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [ParaClassSdmSuffixUpdateComponent]
            })
                .overrideTemplate(ParaClassSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ParaClassSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ParaClassSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ParaClassSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.paraClass = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ParaClassSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.paraClass = entity;
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
