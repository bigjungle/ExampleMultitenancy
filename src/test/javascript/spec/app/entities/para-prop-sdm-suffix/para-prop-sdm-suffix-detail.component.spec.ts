/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { ParaPropSdmSuffixDetailComponent } from 'app/entities/para-prop-sdm-suffix/para-prop-sdm-suffix-detail.component';
import { ParaPropSdmSuffix } from 'app/shared/model/para-prop-sdm-suffix.model';

describe('Component Tests', () => {
    describe('ParaPropSdmSuffix Management Detail Component', () => {
        let comp: ParaPropSdmSuffixDetailComponent;
        let fixture: ComponentFixture<ParaPropSdmSuffixDetailComponent>;
        const route = ({ data: of({ paraProp: new ParaPropSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [ParaPropSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ParaPropSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ParaPropSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.paraProp).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
