package com.anodyzed.vyta.util;

import java.util.Set;

import org.jetbrains.annotations.Contract;

/**
 * Strings
 *
 * @author Chris Pratt
 * @since 2018-09-21
 */
@SuppressWarnings({"unused","WeakerAccess"})
public class Strings {

  /**
   * private Static-Singleton constructor
   */
  private Strings () {
  } //Strings

  /**
   * <p>Checks if a CharSequence is empty ("") or null.</p>
   * <p>
   * <pre>
   * Strings.isEmpty(null)      = true
   * Strings.isEmpty("")        = true
   * Strings.isEmpty(" ")       = false
   * Strings.isEmpty("bob")     = false
   * Strings.isEmpty("  bob  ") = false
   * </pre>
   * <p>
   *
   * @param cs the CharSequence to check, may be null
   * @return {@code true} if the CharSequence is empty or null
   */
  @Contract("null -> true")
  public static boolean isEmpty (final CharSequence cs) {
    return (cs == null) || (cs.length() == 0);
  } //isEmpty

  /**
   * <p>Checks if a CharSequence is not empty ("") and not null.</p>
   * <p>
   * <pre>
   * Strings.isNotEmpty(null)      = false
   * Strings.isNotEmpty("")        = false
   * Strings.isNotEmpty(" ")       = true
   * Strings.isNotEmpty("bob")     = true
   * Strings.isNotEmpty("  bob  ") = true
   * </pre>
   *
   * @param cs the CharSequence to check, may be null
   * @return {@code true} if the CharSequence is not empty and not null
   */
  @Contract("null -> false")
  public static boolean isNotEmpty (final CharSequence cs) {
    return (cs != null) && (cs.length() > 0);
  } //isNotEmpty

  /**
   * <p>Checks if a CharSequence is whitespace, empty ("") or null.</p>
   * <p>
   * <pre>
   * Strings.isBlank(null)      = true
   * Strings.isBlank("")        = true
   * Strings.isBlank(" ")       = true
   * Strings.isBlank("bob")     = false
   * Strings.isBlank("  bob  ") = false
   * </pre>
   *
   * @param cs the CharSequence to check, may be null
   * @return {@code true} if the CharSequence is null, empty or whitespace
   */
  @Contract("null -> true")
  public static boolean isBlank (final CharSequence cs) {
    return (cs == null) || cs.chars().allMatch(Character::isWhitespace);
  } //isBlank

  /**
   * <p>Checks if a CharSequence is not empty (""), not null and not whitespace only.</p>
   * <p>
   * <pre>
   * Strings.isNotBlank(null)      = false
   * Strings.isNotBlank("")        = false
   * Strings.isNotBlank(" ")       = false
   * Strings.isNotBlank("bob")     = true
   * Strings.isNotBlank("  bob  ") = true
   * </pre>
   *
   * @param cs the CharSequence to check, may be null
   * @return {@code true} if the CharSequence is not empty and not null and not whitespace
   */
  @Contract("null -> false")
  public static boolean isNotBlank (final CharSequence cs) {
    return (cs != null) && cs.chars().noneMatch(Character::isWhitespace);
  } //isNotBlank

  /**
   * <p>Checks if the CharSequence contains only Unicode digits.
   * Sign characters and the decimal point are not Unicode digits and returns false.</p>
   * <p>
   * <p>{@code null} will return {@code false}.
   * An empty CharSequence (length()=0) will return {@code false}.</p>
   * <p>
   * <p>Note that the method does not allow for a leading sign, either positive or negative.
   * Also, if a String passes the numeric test, it may still generate a NumberFormatException when parsed by Integer.parseInt or Long.parseLong, e.g. if the value is outside the range for int or long respectively.</p>
   * <p>
   * <pre>
   * Strings.isNumeric(null)   = false
   * Strings.isNumeric("")     = false
   * Strings.isNumeric("  ")   = false
   * Strings.isNumeric("123")  = true
   * Strings.isNumeric("12 3") = false
   * Strings.isNumeric("ab2c") = false
   * Strings.isNumeric("12-3") = false
   * Strings.isNumeric("12.3") = false
   * Strings.isNumeric("-123") = false
   * Strings.isNumeric("+123") = false
   * </pre>
   *
   * @param cs the CharSequence to check, may be null
   * @return {@code true} if only contains digits, and is non-null
   * @since 3.0 Changed "" to return false and not true
   */
  @Contract("null -> false")
  public static boolean isNumeric (final CharSequence cs) {
    return isNotBlank(cs) && cs.chars().allMatch(Character::isDigit);
  } //isNumeric

  /**
   * <p>Checks if the CharSequence contains only Unicode digits allowing for a
   * single decimal point.</p>
   * <p>
   * <p>{@code null} or an empty CharSequence (length()=0) will return {@code false}.</p>
   * <p>
   * <p>Note that the method allows for a leading sign, either positive ('+') or negative ('-').
   * Also, if a String passes the numeric test, it may still generate a NumberFormatException when parsed by Integer.parseInt or Long.parseLong, e.g. if the value is outside the range for int or long respectively.</p>
   * <p>
   * <pre>
   * Strings.isDecimal(null)     = false
   * Strings.isDecimal("")       = false
   * Strings.isDecimal("  ")     = false
   * Strings.isDecimal("123")    = true
   * Strings.isDecimal("12 3")   = false
   * Strings.isDecimal("ab2c")   = false
   * Strings.isDecimal("12-3")   = false
   * Strings.isDecimal("12.3")   = true
   * Strings.isDecimal("-123")   = true
   * Strings.isDecimal("+123.0") = true
   * </pre>
   *
   * @param cs the CharSequence to check, may be null
   * @return {@code true} if only contains digits, and is non-null
   */
  @Contract("null -> false")
  public static boolean isDecimal (final CharSequence cs) {
    if(cs != null) {
      final int nd = cs.length();
      if(nd > 0) {
        int st = 0;
        boolean dotFound = false;
        char c = cs.charAt(0);
        if((c == '-') || (c == '+')) {
          ++st;
        }
        for(int i = st;i < nd;i++) {
          if(!Character.isDigit(c = cs.charAt(i))) {
            if((c != '.') || dotFound) {
              return false;
            } else {
              dotFound = true;
            }
          }
        }
        return true;
      }
    }
    return false;
  } //isDecimal

  /**
   * Replace all instances of <code>from</code> in the sequence with <code>to</code>.
   *
   * @param cs The Character Sequences
   * @param from The character to be replaced
   * @param to The replacement character
   * @return The character sequence as a String with <code>from</code> characters replaced with <code>to</code>
   */
  @Contract("null,_,_ -> null")
  public static String replace (final CharSequence cs,char from,char to) {
    if(isNotEmpty(cs)) {
      char c;
      int j = 0;
      char[] buf = new char[cs.length()];
      for(int i = 0;i < cs.length();i++) {
        if((c = cs.charAt(i)) == from) {
          if(to != '\0') {
            buf[j++] = to;
          }
        } else {
          buf[j++] = c;
        }
      }
      return new String(buf,0,j);
    }
    return null;
  } //replace

  /**
   * Remove all instances of the specified character from the sequence.
   *
   * @param cs The Character Sequence
   * @param ch The character to remove
   * @return The character sequence as a String without the specified character
   */
  @Contract("null,_ -> null")
  public static String remove (final CharSequence cs,char ch) {
    if(isNotEmpty(cs)) {
      char c;
      int j = 0;
      char[] buf = new char[cs.length()];
      for(int i = 0;i < cs.length();i++) {
        if((c = cs.charAt(i)) != ch) {
          buf[j++] = c;
        }
      }
      return new String(buf,0,j);
    }
    return null;
  } //remove

  /**
   * Remove all instances of the specified characters from the sequence.
   *
   * @param cs The Character Sequence
   * @param forbid The set of characters to remove
   * @return The character sequence as a String without the specified characters
   */
  @Contract("null,_ -> null")
  public static String removeAll (final CharSequence cs,Set<Character> forbid) {
    if(isNotEmpty(cs)) {
      char c;
      int j = 0;
      char[] buf = new char[cs.length()];
      for(int i = 0;i < cs.length();i++) {
        if(!forbid.contains(c = cs.charAt(i))) {
          buf[j++] = c;
        }
      }
      return new String(buf,0,j);
    }
    return null;
  } //removeAll

  /**
   * Remove all instances of the specified characters from the sequence.
   *
   * @param cs The Character Sequence
   * @param forbid The characters to remove
   * @return The character sequence as a String without the specified characters
   */
  @Contract("null,_ -> null")
  public static String removeAll (final CharSequence cs,char... forbid) {
    return (isNotEmpty(cs)) ? removeAll(cs,Sets.of(forbid)) : null;
  } //removeAll

  /**
   * Retain all instances of the specified characters from the sequence.
   *
   * @param cs The Character Sequence
   * @param allowed The set of characters to allow
   * @return The character sequence as a String with only the specified characters
   */
  @Contract("null,_ -> null")
  public static String retainAll (final CharSequence cs,Set<Character> allowed) {
    if(isNotEmpty(cs)) {
      char c;
      int j = 0;
      char[] buf = new char[cs.length()];
      for(int i = 0;i < cs.length();i++) {
        if(allowed.contains(c = cs.charAt(i))) {
          buf[j++] = c;
        }
      }
      return new String(buf,0,j);
    }
    return null;
  } //retainAll

  /**
   * Retain all instances of the specified characters from the sequence.
   *
   * @param cs The Character Sequence
   * @param allowed The characters to allow
   * @return The character sequence as a String with only the specified characters
   */
  @Contract("null,_ -> null")
  public static String retainAll (final CharSequence cs,char... allowed) {
    return (isNotEmpty(cs)) ? retainAll(cs,Sets.of(allowed)) : null;
  } //retainAll

  /**
   *  Generate a String containing <code>count</code> instances of the
   *  <code>ch</code> character
   *
    * @param ch The character to repeat
   * @param count The number of times to repeat
   * @return the string of <code>count</code> <code>ch</code>'s
   */
  public static String repeat (char ch,int count) {
    StringBuilder str = new StringBuilder();
    for(int i = 0;i < count;i++) {
      str.append(ch);
    }
    return str.toString();
  } //repeat

  /**
   * Generate a String containing <code>count</code> instances of the
   * <code>sub</code> substring
   *
   * @param sub   The substring to repeat
   * @param count The number of times to repeat
   * @return the string of <code>count</code> instances of <code>sub</code>
   */
  public static String repeat (String sub,int count) {
    StringBuilder str = new StringBuilder();
    for(int i = 0;i < count;i++) {
      str.append(sub);
    }
    return str.toString();
  } //repeat

} //*Strings
