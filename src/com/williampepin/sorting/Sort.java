package com.williampepin.sorting;

public class Sort {
  public static void bubbleSort(int[] array){
    boolean isSorted;

    for(var i=0; i< array.length; i++){
      isSorted = true;

      for(var j = 1; j < array.length - i;j++)
        if(array[j] < array[j-1]){
          swap(array,j,j-1);
          isSorted = false;
        }

      if(isSorted)
        return;
    }
  }

  public static void selectionSort(int[] array){
    for (int i = 0; i < array.length; i++) {
      swap(array,findMinIndex(array,i),i);
    }
  }

  public static void insertionSort(int[] array){
    for (int i = 0; i < array.length; i++) {
      var current = array[i];
      var j = i - 1;
      while(j >= 0 && array[j] > current){
        array[j + 1] = array[j];
        j--;
      }
      array[j + 1] = current;
    }
  }

  public static void MergeSort(int[] array){
    if(array.length < 2) return;

    var middle = array.length / 2;

    int[] left = new int[middle];
    for (int i = 0; i < middle; i++)
      left[i] = array[i];

    int[] right = new int[array.length - middle];
    for (int i = middle; i < array.length; i++)
      right[i - middle] = array[i];

    MergeSort(left);
    MergeSort(right);

    merge(left,right,array);
  }

  private static void merge(int[] left, int[] right, int[] result){
    int i = 0, j = 0, k = 0;

    while(i < left.length && j < right.length)
    {
      if(left[i] <= right[j])
        result[k++] = left[i++];
      else
        result[k++] = right[j++];
    }

    while(i < left.length)
      result[k++] = left [i++];
    while(j < right.length)
      result[k++] = right[j++];
  }

  private static void QuickSort(int array[]){
    
  }

  private static int findMinIndex(int array[], int start){
    var minIndex = start;

    for (int j = start; j < array.length; j++) {
      if(array[j] < array[minIndex])
        minIndex = j;
    }
    return minIndex;
  }


  private static void swap(int[] array, int i1, int i2){
    var temp = array[i1];
    array[i1] = array[i2];
    array[i2] = temp;
  }
}
