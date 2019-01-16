import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    PlanInfoStepAtchSdmSuffixComponent,
    PlanInfoStepAtchSdmSuffixDetailComponent,
    PlanInfoStepAtchSdmSuffixUpdateComponent,
    PlanInfoStepAtchSdmSuffixDeletePopupComponent,
    PlanInfoStepAtchSdmSuffixDeleteDialogComponent,
    planInfoStepAtchRoute,
    planInfoStepAtchPopupRoute
} from './';

const ENTITY_STATES = [...planInfoStepAtchRoute, ...planInfoStepAtchPopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PlanInfoStepAtchSdmSuffixComponent,
        PlanInfoStepAtchSdmSuffixDetailComponent,
        PlanInfoStepAtchSdmSuffixUpdateComponent,
        PlanInfoStepAtchSdmSuffixDeleteDialogComponent,
        PlanInfoStepAtchSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        PlanInfoStepAtchSdmSuffixComponent,
        PlanInfoStepAtchSdmSuffixUpdateComponent,
        PlanInfoStepAtchSdmSuffixDeleteDialogComponent,
        PlanInfoStepAtchSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbPlanInfoStepAtchSdmSuffixModule {}
