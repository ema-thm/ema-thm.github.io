import {Statistic} from './statistic.model';
import {Record} from './record.model';

describe('Statistic', () => {
  const records = [
    new Record(1, 'CS1013', 'Objektorientierte Programmierung', 6, 73, true, true, 2016),
    new Record(2, 'MN1007', 'Diskrete Mathematik', 6, 81, true, false, 2016),
    new Record(3, 'CS1019', 'Compilerbau', 6, 81, false, false, 2017),
    new Record(4, 'CS1020', 'Datenbanksysteme', 6, 92, false, false, 2017)
  ];

  const stats = new Statistic(records);
  const statsNoRecords = new Statistic([]);

  it('should create an instance', () => {
    expect(stats).toBeTruthy();
    expect(statsNoRecords).toBeTruthy();
  });

  it('counts all records', () => {
    expect(stats.recordCount).toBe(4);
    expect(statsNoRecords.recordCount).toBe(0);
  });

  it('counts half weighted records', () => {
    expect(stats.hwCount).toBe(2);
    expect(statsNoRecords.hwCount).toBe(0);
  });

  it('sums up credit points', () => {
    expect(stats.sumCrp).toBe(24);
    expect(statsNoRecords.sumCrp).toBe(0);
  });

  it('calculates missing credit points to graduation', () => {
    expect(stats.crpToEnd).toBe(156);
    expect(statsNoRecords.crpToEnd).toBe(180);
  });

  it('calculates average grade', () => {
    expect(stats.averageGrade).toBe(83);
    expect(statsNoRecords.averageGrade).toBe(0);
  });

});
