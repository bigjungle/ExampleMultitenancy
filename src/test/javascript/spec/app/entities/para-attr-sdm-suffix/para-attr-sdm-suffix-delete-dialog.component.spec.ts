/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlandbTestModule } from '../../../test.module';
import { ParaAttrSdmSuffixDeleteDialogComponent } from 'app/entities/para-attr-sdm-suffix/para-attr-sdm-suffix-delete-dialog.component';
import { ParaAttrSdmSuffixService } from 'app/entities/para-attr-sdm-suffix/para-attr-sdm-suffix.service';

describe('Component Tests', () => {
    describe('ParaAttrSdmSuffix Management Delete Component', () => {
        let comp: ParaAttrSdmSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<ParaAttrSdmSuffixDeleteDialogComponent>;
        let service: ParaAttrSdmSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [ParaAttrSdmSuffixDeleteDialogComponent]
            })
                .overrideTemplate(ParaAttrSdmSuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ParaAttrSdmSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ParaAttrSdmSuffixService);
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
