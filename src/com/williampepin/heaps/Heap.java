package com.williampepin.heaps;

public class Heap {
  private int[] items = new int[10];
  private int size = 0;

  public void insert(int value){
    if(isFull())
      throw new IllegalStateException();
    items[size++] = value;
    bubbleUp();
  }

  public int remove(){
    if(isEmpty())
      throw new IllegalStateException();
    var root = items[0];
    items[0] = items[--size];

    bubbleDown();
    return root;
  }

  public boolean isEmpty(){
    return size == 0;
  }

  public boolean isFull(){
    return size == items.length;
  }

  public int max(){
    if(isEmpty())
      throw new IllegalStateException();
    return items[0];
  }

  public static int[] sortDescending(int[] numbers){
    var heap = new Heap();
    for (var number:numbers)
      heap.insert(number);
    for (int i = 0; i < numbers.length; i++) {
      numbers[i] = heap.remove();
    }
    return numbers;
  }

  public static int[] sortAscending(int[] numbers){
    var heap = new Heap();
    for (var number:numbers)
      heap.insert(number);
    for (int i = numbers.length - 1; i >= 0; i--) {
      numbers[i] = heap.remove();
    }
    return numbers;
  }

  public static void heapify(int[] array){
    var lastParentindex = array.length / 2 - 1;
    for (int i = lastParentindex; i >= 0; i--) {
      heapify(array,i);
    }
  }

  public static int GetKthLargest(int[] array, int k){
    if(k< 1 || k > array.length)
      throw new IllegalArgumentException();

    var heap = new Heap();
    for (var number: array)
      heap.insert(number);

    for (int i = 0; i < k - 1; i++) {
      heap.remove();
    }
    return heap.max();
  }

  private static void heapify(int[] array, int index){
    var largerIndex = index;
    var leftindex = leftChildIndex(index);
    if( leftindex < array.length &&
        array[leftindex] > array[largerIndex])
      largerIndex = leftindex;

    var rightIndex = rightChildIndex(index);
    if(rightIndex < array.length &&
        array[rightIndex] > array[largerIndex])
      largerIndex = rightIndex;

    if (index == largerIndex)
      return;

    swap(array,index,largerIndex);
    heapify(array,largerIndex);
  }

  private boolean isParentValid(int index){
    if(!hasLeftChild(index))
      return true;

    var isValid = items[index] >= leftChild(index);

    if(hasRightChild(index))
      isValid &= items[index] >= rightChild(index);

    return isValid;
  }

  private void bubbleDown(){
    var index = 0;
    while(index <= size && !isParentValid(index)){
      swap(index,largerChildIndex(index));
      index = largerChildIndex(index);
    }
  }


  private void bubbleUp(){
    var index = size - 1;
    while(index > 0 && items[index] > parent(index)){
      swap(index, parentIndex(index));
      index = parentIndex(index);
    }
  }

  private void swap(int index1, int index2){
    var temp = items[index1];
    items[index1] = items[index2];
    items[index2] = temp;
  }

  private static void swap(int[] array, int index1, int index2)
  {
    var temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
  }

  private int parent(int index){
    return items[parentIndex(index)];
  }

  private static int parentIndex(int index){
    return (index-1) / 2;
  }

  private int leftChild(int index){
    return items[leftChildIndex(index)];
  }

  private static int leftChildIndex(int index) {
    return index * 2 + 1;
  }

  private int rightChild(int index){
    return items[rightChildIndex(index)];
  }

  private static int rightChildIndex(int index){
    return index * 2 + 2;
  }

  private int largerChildIndex(int index){
    if(!hasLeftChild(index))
      return index;

    if(!hasRightChild(index))
      return leftChildIndex(index);

    return (leftChild(index) > rightChild(index))
        ? leftChildIndex(index)
        : rightChildIndex(index);
  }

  private boolean hasLeftChild(int index){
    return leftChildIndex(index) <= size;
  }

  private boolean hasRightChild(int index){
    return rightChildIndex(index) <= size;
  }
}
