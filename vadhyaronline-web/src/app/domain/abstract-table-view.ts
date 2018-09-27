export class AbstractTableView<T> {

  actualData: Array<T> = [];
  displayData: Array<T> = [];
  loading: boolean;
  sortKey: string;
  sortValue: string;

  sort(sort: { key: string, value: string }): void {
    this.loading = true;
    this.displayData = [];
    const dataList = [...this.actualData];
    this.sortKey = sort.key;
    this.sortValue = sort.value;
    if (this.sortKey && this.sortValue) {
      this.displayData = [...dataList.sort((a, b) => (this.sortValue === 'ascend')
        ? (a[this.sortKey] > b[this.sortKey] ? 1 : -1) : (b[this.sortKey] > a[this.sortKey] ? 1 : -1))];
    } else {
      this.displayData = [...dataList];
    }
    this.loading = false;
  }

  setData(data: Array<T>) {
    this.actualData = data;
    this.displayData = data;
  }

}
