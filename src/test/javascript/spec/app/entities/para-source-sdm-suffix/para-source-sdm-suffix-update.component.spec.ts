/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { ParaSourceSdmSuffixUpdateComponent } from 'app/entities/para-source-sdm-suffix/para-source-sdm-suffix-update.component';
import { ParaSourceSdmSuffixService } from 'app/entities/para-source-sdm-suffix/para-source-sdm-suffix.service';
import { ParaSourceSdmSuffix } from 'app/shared/model/para-source-sdm-suffix.model';

describe('Component Tests', () => {
    describe('ParaSourceSdmSuffix Management Update Component', () => {
        let comp: ParaSourceSdmSuffixUpdateComponent;
        let fixture: ComponentFixture<ParaSourceSdmSuffixUpdateComponent>;
        let service: ParaSourceSdmSuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [ParaSourceSdmSuffixUpdateComponent]
            })
                .overrideTemplate(ParaSourceSdmSuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ParaSourceSdmSuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ParaSourceSdmSuffixService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ParaSourceSdmSuffix(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.paraSource = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ParaSourceSdmSuffix();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.paraSource = entity;
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
