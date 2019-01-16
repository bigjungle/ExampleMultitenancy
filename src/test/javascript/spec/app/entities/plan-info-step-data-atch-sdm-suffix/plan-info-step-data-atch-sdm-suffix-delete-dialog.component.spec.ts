/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoStepDataAtchSdmSuffixDeleteDialogComponent } from 'app/entities/plan-info-step-data-atch-sdm-suffix/plan-info-step-data-atch-sdm-suffix-delete-dialog.component';
import { PlanInfoStepDataAtchSdmSuffixService } from 'app/entities/plan-info-step-data-atch-sdm-suffix/plan-info-step-data-atch-sdm-suffix.service';

describe('Component Tests', () => {
    describe('PlanInfoStepDataAtchSdmSuffix Management Delete Component', () => {
        let comp: PlanInfoStepDataAtchSdmSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<PlanInfoStepDataAtchSdmSuffixDeleteDialogComponent>;
        let service: PlanInfoStepDataAtchSdmSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoStepDataAtchSdmSuffixDeleteDialogComponent]
            })
                .overrideTemplate(PlanInfoStepDataAtchSdmSuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanInfoStepDataAtchSdmSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanInfoStepDataAtchSdmSuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
