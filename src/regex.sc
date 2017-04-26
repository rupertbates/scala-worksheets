"""\{(\d+)\}""".r.replaceAllIn("""{123} {ABC} {45}""", m=>(m.group(1).toInt + 1).toString)


"x(\\d)".r.replaceAllIn("""Sapphire and Steel 2x1.mp4""", m => "x" + (m.group(1).toInt + 1).toString)
