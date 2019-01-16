/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { RmsPersonSdmSuffixService } from 'app/entities/rms-person-sdm-suffix/rms-person-sdm-suffix.service';
import { IRmsPersonSdmSuffix, RmsPersonSdmSuffix, ValidType } from 'app/shared/model/rms-person-sdm-suffix.model';

describe('Service Tests', () => {
    describe('RmsPersonSdmSuffix Service', () => {
        let injector: TestBed;
        let service: RmsPersonSdmSuffixService;
        let httpMock: HttpTestingController;
        let elemDefault: IRmsPersonSdmSuffix;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(RmsPersonSdmSuffixService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new RmsPersonSdmSuffix(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                false,
                currentDate,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                false,
                'AAAAAAA',
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                ValidType.LONG,
                currentDate,
                currentDate,
                currentDate,
                currentDate,
                currentDate,
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        birthday: currentDate.format(DATE_TIME_FORMAT),
                        validBegin: currentDate.format(DATE_TIME_FORMAT),
                        validEnd: currentDate.format(DATE_TIME_FORMAT),
                        insertTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT),
                        verifyTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a RmsPersonSdmSuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        birthday: currentDate.format(DATE_TIME_FORMAT),
                        validBegin: currentDate.format(DATE_TIME_FORMAT),
                        validEnd: currentDate.format(DATE_TIME_FORMAT),
                        insertTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT),
                        verifyTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        birthday: currentDate,
                        validBegin: currentDate,
                        validEnd: currentDate,
                        insertTime: currentDate,
                        updateTime: currentDate,
                        verifyTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new RmsPersonSdmSuffix(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a RmsPersonSdmSuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        personName: 'BBBBBB',
                        firstName: 'BBBBBB',
                        jobId: 'BBBBBB',
                        lastName: 'BBBBBB',
                        otherName: 'BBBBBB',
                        sex: true,
                        birthday: currentDate.format(DATE_TIME_FORMAT),
                        pic: 'BBBBBB',
                        icon: 'BBBBBB',
                        mobile: 'BBBBBB',
                        depAddress: 'BBBBBB',
                        depCode: 'BBBBBB',
                        dutyId: 'BBBBBB',
                        dimissionFlag: true,
                        headerFlag: true,
                        satrapFlag: true,
                        leaderFlag: true,
                        postFlag1: true,
                        postFlag2: true,
                        postFlag3: true,
                        officeTel: 'BBBBBB',
                        fax: 'BBBBBB',
                        mail1: 'BBBBBB',
                        mail2: 'BBBBBB',
                        familyTel: 'BBBBBB',
                        familyAdd: 'BBBBBB',
                        familyCode: 'BBBBBB',
                        personDesc: 'BBBBBB',
                        idCode: 'BBBBBB',
                        pop3: 'BBBBBB',
                        smtp: 'BBBBBB',
                        mailUsername: 'BBBBBB',
                        mailPassword: 'BBBBBB',
                        mailKeepFlag: true,
                        personSort: 'BBBBBB',
                        levelNum: 1,
                        personStateId: 'BBBBBB',
                        by01: 'BBBBBB',
                        by02: 'BBBBBB',
                        by03: 'BBBBBB',
                        by04: 'BBBBBB',
                        by05: 'BBBBBB',
                        by06: 'BBBBBB',
                        by07: 'BBBBBB',
                        by08: 'BBBBBB',
                        by09: 'BBBBBB',
                        by10: 'BBBBBB',
                        validType: 'BBBBBB',
                        validBegin: currentDate.format(DATE_TIME_FORMAT),
                        validEnd: currentDate.format(DATE_TIME_FORMAT),
                        insertTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT),
                        verifyTime: currentDate.format(DATE_TIME_FORMAT),
                        serialNumber: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        birthday: currentDate,
                        validBegin: currentDate,
                        validEnd: currentDate,
                        insertTime: currentDate,
                        updateTime: currentDate,
                        verifyTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of RmsPersonSdmSuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        personName: 'BBBBBB',
                        firstName: 'BBBBBB',
                        jobId: 'BBBBBB',
                        lastName: 'BBBBBB',
                        otherName: 'BBBBBB',
                        sex: true,
                        birthday: currentDate.format(DATE_TIME_FORMAT),
                        pic: 'BBBBBB',
                        icon: 'BBBBBB',
                        mobile: 'BBBBBB',
                        depAddress: 'BBBBBB',
                        depCode: 'BBBBBB',
                        dutyId: 'BBBBBB',
                        dimissionFlag: true,
                        headerFlag: true,
                        satrapFlag: true,
                        leaderFlag: true,
                        postFlag1: true,
                        postFlag2: true,
                        postFlag3: true,
                        officeTel: 'BBBBBB',
                        fax: 'BBBBBB',
                        mail1: 'BBBBBB',
                        mail2: 'BBBBBB',
                        familyTel: 'BBBBBB',
                        familyAdd: 'BBBBBB',
                        familyCode: 'BBBBBB',
                        personDesc: 'BBBBBB',
                        idCode: 'BBBBBB',
                        pop3: 'BBBBBB',
                        smtp: 'BBBBBB',
                        mailUsername: 'BBBBBB',
                        mailPassword: 'BBBBBB',
                        mailKeepFlag: true,
                        personSort: 'BBBBBB',
                        levelNum: 1,
                        personStateId: 'BBBBBB',
                        by01: 'BBBBBB',
                        by02: 'BBBBBB',
                        by03: 'BBBBBB',
                        by04: 'BBBBBB',
                        by05: 'BBBBBB',
                        by06: 'BBBBBB',
                        by07: 'BBBBBB',
                        by08: 'BBBBBB',
                        by09: 'BBBBBB',
                        by10: 'BBBBBB',
                        validType: 'BBBBBB',
                        validBegin: currentDate.format(DATE_TIME_FORMAT),
                        validEnd: currentDate.format(DATE_TIME_FORMAT),
                        insertTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT),
                        verifyTime: currentDate.format(DATE_TIME_FORMAT),
                        serialNumber: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        birthday: currentDate,
                        validBegin: currentDate,
                        validEnd: currentDate,
                        insertTime: currentDate,
                        updateTime: currentDate,
                        verifyTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a RmsPersonSdmSuffix', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
