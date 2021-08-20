package com.contrastsecurity.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Describes a page of results from the Scan API.
 *
 * <p>This class is package-private, because this package does not yet support paging through
 * results from the Contrast Scan API; therefore, this remains an implementation detail of this
 * package.
 *
 * <p>We may add public support for paging through scan results in a future version of the Contrast
 * Java SDK.
 *
 * @param <T> the type of item in the page
 */
public final class ScanPagedResult<T> {

  private final List<T> content;
  private final int totalElements;

  /**
   * @param content page contents
   * @param totalElements total number of elements matching the query (not the number of elements in
   *     this page)
   */
  ScanPagedResult(final List<T> content, final int totalElements) {
    this.content = Collections.unmodifiableList(new ArrayList<>(content));
    this.totalElements = totalElements;
  }

  /** @return page contents */
  public List<T> getContent() {
    return content;
  }

  /**
   * @return total number of elements matching the query (not the number of elements in this page)
   */
  public int getTotalElements() {
    return totalElements;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final ScanPagedResult<?> that = (ScanPagedResult<?>) o;
    return totalElements == that.totalElements && content.equals(that.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, totalElements);
  }

  @Override
  public String toString() {
    return "ScanPagedResult{" + "content=" + content + ", totalElements=" + totalElements + '}';
  }
}
