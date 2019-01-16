/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoSdmSuffixDeleteDialogComponent } from 'app/entities/plan-info-sdm-suffix/plan-info-sdm-suffix-delete-dialog.component';
import { PlanInfoSdmSuffixService } from 'app/entities/plan-info-sdm-suffix/plan-info-sdm-suffix.service';

describe('Component Tests', () => {
    describe('PlanInfoSdmSuffix Management Delete Component', () => {
        let comp: PlanInfoSdmSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<PlanInfoSdmSuffixDeleteDialogComponent>;
        let service: PlanInfoSdmSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoSdmSuffixDeleteDialogComponent]
            })
                .overrideTemplate(PlanInfoSdmSuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanInfoSdmSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanInfoSdmSuffixService);
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
