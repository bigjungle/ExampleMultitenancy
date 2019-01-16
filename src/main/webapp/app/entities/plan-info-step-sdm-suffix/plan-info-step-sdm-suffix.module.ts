import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    PlanInfoStepSdmSuffixComponent,
    PlanInfoStepSdmSuffixDetailComponent,
    PlanInfoStepSdmSuffixUpdateComponent,
    PlanInfoStepSdmSuffixDeletePopupComponent,
    PlanInfoStepSdmSuffixDeleteDialogComponent,
    planInfoStepRoute,
    planInfoStepPopupRoute
} from './';

const ENTITY_STATES = [...planInfoStepRoute, ...planInfoStepPopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PlanInfoStepSdmSuffixComponent,
        PlanInfoStepSdmSuffixDetailComponent,
        PlanInfoStepSdmSuffixUpdateComponent,
        PlanInfoStepSdmSuffixDeleteDialogComponent,
        PlanInfoStepSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        PlanInfoStepSdmSuffixComponent,
        PlanInfoStepSdmSuffixUpdateComponent,
        PlanInfoStepSdmSuffixDeleteDialogComponent,
        PlanInfoStepSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbPlanInfoStepSdmSuffixModule {}
