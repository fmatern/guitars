/**
 * Custom annotation for patterns
 */

package org.wahlzeit.utils;

public @interface Pattern {
		String name() default "";
		String[] participants() default "";
}
