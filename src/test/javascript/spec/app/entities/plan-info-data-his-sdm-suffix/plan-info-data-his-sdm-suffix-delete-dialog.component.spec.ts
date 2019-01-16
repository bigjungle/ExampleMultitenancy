/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlandbTestModule } from '../../../test.module';
import { PlanInfoDataHisSdmSuffixDeleteDialogComponent } from 'app/entities/plan-info-data-his-sdm-suffix/plan-info-data-his-sdm-suffix-delete-dialog.component';
import { PlanInfoDataHisSdmSuffixService } from 'app/entities/plan-info-data-his-sdm-suffix/plan-info-data-his-sdm-suffix.service';

describe('Component Tests', () => {
    describe('PlanInfoDataHisSdmSuffix Management Delete Component', () => {
        let comp: PlanInfoDataHisSdmSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<PlanInfoDataHisSdmSuffixDeleteDialogComponent>;
        let service: PlanInfoDataHisSdmSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [PlanInfoDataHisSdmSuffixDeleteDialogComponent]
            })
                .overrideTemplate(PlanInfoDataHisSdmSuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanInfoDataHisSdmSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlanInfoDataHisSdmSuffixService);
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
