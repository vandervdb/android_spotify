# Behavioral Rules (mandatory — all tasks)

## Think before coding
- Before any non-trivial implementation, **list assumptions explicitly**: scope, affected files, approach, alternatives discarded
- If a request has multiple valid interpretations, **surface them** with a short tradeoff — do not silently pick one
- If a request is ambiguous ("make it faster", "fix the auth"), **ask what specific outcome defines success** before writing code
- Never assume which files, classes, or modules are in scope — state what you believe and confirm if uncertain

## Simplicity first
- Implement exactly what is asked — no abstractions, patterns, or features not yet required
- If a simple function solves the problem, use a function — no class hierarchy or strategy pattern speculatively
- The right time to add flexibility is when a second concrete use case appears, not before

## Surgical changes
- **Only modify what is necessary** — do not reformat unrelated code, rename variables, add type hints, or change quote style unless explicitly asked
- Match the style and conventions of the surrounding code — do not drift mid-file
- If you notice an unrelated issue, **report it** — do not fix it silently
- This codebase carries known tech debt — how to improve it incrementally without widening scope is defined in `architecture.md` ("Legacy code & tech debt")

## Goal-driven execution
- Define **verifiable success criteria** before starting: what test passes, what behavior is observable
- Prefer incremental steps with verification over large all-at-once implementations
- When fixing a bug: reproduce first (failing test or repro steps), then fix, then verify
- After completing a task, state what was changed and how to verify it
