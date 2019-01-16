/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { ParaAttrSdmSuffixUpdateComponent } from 'app/entities/para-attr-sdm-suffix/para-attr-sdm-suffix-update.component';
import { ParaAttrSdmSuffixService } from 'app/entities/para-attr-sdm-suffix/para-attr-sdm-suffix.service';
import { ParaAttrSdmSuffix } from 'app/shared/model/para-attr-sdm-suffix.model';

describe('Component Tests', () => {
    describe('ParaAttrSdmSuffix Management Update Component', () => {
        let comp: ParaAttrSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<ParaAttrSdmSuffixUpdateComponent>;
        let service: ParaAttrSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [ParaAttrSdmSuffixUpdateComponent]
            })
                .overrideTemplate(ParaAttrSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ParaAttrSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ParaAttrSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ParaAttrSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.paraAttr = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ParaAttrSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.paraAttr = entity;
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
