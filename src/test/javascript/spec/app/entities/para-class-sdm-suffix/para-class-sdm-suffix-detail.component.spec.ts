/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { ParaClassSdmSuffixDetailComponent } from 'app/entities/para-class-sdm-suffix/para-class-sdm-suffix-detail.component';
import { ParaClassSdmSuffix } from 'app/shared/model/para-class-sdm-suffix.model';

describe('Component Tests', () => {
    describe('ParaClassSdmSuffix Management Detail Component', () => {
        let comp: ParaClassSdmSuffixDetailComponent;
        let fixture: ComponentFixture<ParaClassSdmSuffixDetailComponent>;
        const route = ({ data: of({ paraClass: new ParaClassSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [ParaClassSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ParaClassSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ParaClassSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.paraClass).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
