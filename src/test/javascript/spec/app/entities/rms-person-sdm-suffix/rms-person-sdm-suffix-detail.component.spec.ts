/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlandbTestModule } from '../../../test.module';
import { RmsPersonSdmSuffixDetailComponent } from 'app/entities/rms-person-sdm-suffix/rms-person-sdm-suffix-detail.component';
import { RmsPersonSdmSuffix } from 'app/shared/model/rms-person-sdm-suffix.model';

describe('Component Tests', () => {
    describe('RmsPersonSdmSuffix Management Detail Component', () => {
        let comp: RmsPersonSdmSuffixDetailComponent;
        let fixture: ComponentFixture<RmsPersonSdmSuffixDetailComponent>;
        const route = ({ data: of({ rmsPerson: new RmsPersonSdmSuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [RmsPersonSdmSuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RmsPersonSdmSuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RmsPersonSdmSuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.rmsPerson).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
