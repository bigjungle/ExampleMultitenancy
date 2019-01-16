/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { ParaSourceSdmSuffixDetailComponent } from 'app/entities/para-source-sdm-suffix/para-source-sdm-suffix-detail.component';
import { ParaSourceSdmSuffix } from 'app/shared/model/para-source-sdm-suffix.model';

describe('Component Tests', () => {
    describe('ParaSourceSdmSuffix Management Detail Component', () => {
        let comp: ParaSourceSdmSuffixDetailComponent;
        let fixture: ComponentFixture<ParaSourceSdmSuffixDetailComponent>;
        const route = ({ data: of({ paraSource: new ParaSourceSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [ParaSourceSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ParaSourceSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ParaSourceSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.paraSource).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
