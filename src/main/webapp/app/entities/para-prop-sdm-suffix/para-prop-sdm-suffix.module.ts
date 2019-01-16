import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    ParaPropSdmSuffixComponent,
    ParaPropSdmSuffixDetailComponent,
    ParaPropSdmSuffixUpdateComponent,
    ParaPropSdmSuffixDeletePopupComponent,
    ParaPropSdmSuffixDeleteDialogComponent,
    paraPropRoute,
    paraPropPopupRoute
} from './';

const ENTITY_STATES = [...paraPropRoute, ...paraPropPopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ParaPropSdmSuffixComponent,
        ParaPropSdmSuffixDetailComponent,
        ParaPropSdmSuffixUpdateComponent,
        ParaPropSdmSuffixDeleteDialogComponent,
        ParaPropSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        ParaPropSdmSuffixComponent,
        ParaPropSdmSuffixUpdateComponent,
        ParaPropSdmSuffixDeleteDialogComponent,
        ParaPropSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbParaPropSdmSuffixModule {}
