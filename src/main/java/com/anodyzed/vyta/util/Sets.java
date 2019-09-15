package com.anodyzed.vyta.util;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Sets
 *
 * @author Chris Pratt
 * @since 2018-09-21
 */
@SuppressWarnings({"unused","WeakerAccess"})
public class Sets {

  /**
   * private Static-Singleton constructor
   */
  private Sets () {
  } //Sets

  /**
   * Null-safe isEmpty for Collections
   *
   * @param col Collection to be tested
   * @return true if <code>col</code> is null or empty
   */
  @Contract("null -> true")
  public static boolean isEmpty (Collection<?> col) {
    return (col == null) || col.isEmpty();
  } //isEmpty

  /**
   * Produce a new HashSet that is the difference (or complement) of the two
   * supplied collections (<code>u \ a</code>). Basically, a set of all
   * members of <code>u</code> that are not members of <code>a</code>.
   *
   * e.g.: u = {1,2,3} a = {2,3,4} result = {1}
   *
   * @param u The universal set
   * @param a The accompanying set
   * @return The set complement
   */
  public static @NotNull <T> Set<T> difference (Collection<T> u,Collection<T> a) {
    Set<T> result = new HashSet<>(u);
    result.removeAll(a);
    return result;
  } //difference

  /**
   * Produce a new HashSet that is the intersection of the supplied
   * collections. Basically, a result set of all members that exist in EVERY
   * source collection.
   *
   * e.g.: a = {1,2,3,4,5}, b = [{4,5,6,7},{3,4,5}], result = {4,5}
   *
   * @param a A Set
   * @param b More Sets
   * @return The intersection set
   */
  @SafeVarargs
  public static @NotNull <T> Set<T> intersection (Collection<T> a,Collection<T>... b) {
    Set<T> result = new HashSet<>(a);
    for(Collection<T> c : b) {
      result.retainAll(c);
    }
    return result;
  } //intersection

  /**
   * Produce a new HashSet that is the intersection of all collections in the
   * supplied iteration.  Basically, the result set of all members that exist
   * in EVERY source collection.
   *
   * e.g: sets = [{1,2,3,4,5},{4,5,6,7},{3,4,5}], result = {4,5}
   *
   * @param sets The collection of sets
   * @return The intersection set
   */
  public static @NotNull <T> Set<T> intersection (Iterable<Collection<T>> sets) {
    Set<T> result = new HashSet<>();
    Iterator<Collection<T>> i = sets.iterator();
    if(i.hasNext()) {
      result.addAll(i.next());
      while(i.hasNext()) {
        result.retainAll(i.next());
      }
    }
    return result;
  } //intersection

  /**
   * Get the First Element from the Iteration
   *
   * @param i An Iterable Collection
   * @return The First Element
   */
  public static @Nullable <T> T getFirst (@NotNull Iterable<T> i) {
    Iterator<T> it = i.iterator();
    return (it.hasNext()) ? it.next() : null;
  } //getFirst

  /**
   * Create a Set from the supplied list of characters
   *
   * @param elements Variable Argument list of chars
   * @return An Un-modifiable Set of Characters
   */
  public static @NotNull Set<Character> of (char... elements) {
    Set<Character> result = new HashSet<>();
    for(char c : elements) {
      result.add(c);
    }
    return Collections.unmodifiableSet(result);
  } //of

} //*Sets
